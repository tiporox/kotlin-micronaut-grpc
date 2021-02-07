## Micronaut 2.3.1 Documentation

- [User Guide](https://docs.micronaut.io/2.3.1/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.3.1/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.3.1/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)



## Micronaut DI

- Micronaut usa compile time para realizar injecao de dependencias, evitando uso de reflaction e proxies
--- Usar reflaction como ultimo recurso
--- Sem proxies
--- Otimizar tempo de start-up
--- Reeduzir o uso de memoria
--- Tratamento de erros compreensiveis

## Beans

- E um objeto que tem o ciclo de vida controlado pelo micronaut, esse ciclo de vida inclui:
--- Criação
--- Execução
--- Destruição

### Escopos:

- @Singleton: faz com que só exista uma instância do bean 
- @Context: o bean deve ser criado no memos momento que a ApplicationContext 
- @Prototype: o bean sera criado toda vez que for injetado
- @Infrastructure: O bean nao pode ser sobrescrito ou trocado usando @Replaces pois é critico para o funcionameto do sistema
- @ThreadLocal: Associa um bean por thread
- @Refreshable: permite que o bean seja refreshed via /refresh
- @RequestScope: uma nova instacia para cada requisição http

-- Eager Initialization: Utilizado mais em contexto de lambdas na aws, para otimizar a inicialização:

	public static void main(String[] args) {
        Micronaut.build(args)
            .eagerInitSingletons(true) 
            .mainClass(Application.class)
            .start();
    }

@ConfigurationReader beans such as @EachProperty or @ConfigurationProper: são beans singleton

@Replaces: Anotacao utilizada para trocar implementação do Bean 
@DefaultImplementation: Usado para prover uma implementacao quando o usario usa @Replaces apontando pra interface

@Configuration:  seta configurações para o bean
@PostConstruct: utilizado em metodos, executa metodo quando o bean e criado
@PreDestroy: utilizado em metodos, executa metodo quando o contexto e fechado

-----------------------

## Events

### Disparando eventos:

@Inject
internal var eventPublisher: ApplicationEventPublisher? = null

eventPublisher!!.publishEvent(SampleEvent())

### Escutando eventos:
Para escutar eventos basta você criar um bean que implementa ApplicationEventListener onde o tipo generico, é o tipo doevento que
deve ser executado


    @Singleton
    class SampleEventListener : ApplicationEventListener<SampleEvent> {
        var invocationCounter = 0
    
        override fun onApplicationEvent(event: SampleEvent) {
            invocationCounter++
        }
    }

ou usar  @EventListener
    
	@EventListener
    internal fun onSampleEvent(event: SampleEvent) {
        invocationCounter++
    }

    @Async: faque que execução do evento ocorra em thread separada

	@EventListener
    @Async
    open fun onSampleEvent(event: SampleEvent) {
        println("Incrementing invocation counter...")
        invocationCounter.getAndIncrement()
    }

------------
Introspecção não usa reflaction otimizando uso de memória

    @Introspected: deixa a classe habilitada para introspecção
    BeanIntrospection: classe usada para introspecçãoo de um classe
    
    val introspection = BeanIntrospection.getIntrospection(User::class.java)
    var user: User = introspection.instantiate("3", "Hugo", 35, "M")
    
    introspection.beanProperties.forEach {
        println("prop: $it")
    }

-----------

@Creator: indica o micronaut qual construtor utilizar quando for criar a instacia do bean, para classes com mais de um contrutor


##  Bean Validation

    implementation("io.micronaut:micronaut-validation")
    
    data class Person(
        @field:NotBlank var name: String,
        @field:Min(18) var age: Int
    )

## HTTP Server

    implementation("io.micronaut:micronaut-http-server-netty")


## Server Events

- ServerStartupEvent: evento disparado quando o servidor termina de iniciar
- ServerShutdownEvent: evento disparado quando o servidor desliga
- ServiceReadyEvent: evento disparado apos todos os listeners de ServerStartupEvent tiverem sido executados e exposto o EmbeddedServerInstance
- ServiceStoppedEvent: evento disparado apos todos os listeners de ServerShutdownEvent e exposto o EmbeddedServerInstance


# gRPC

## O que é?

- Framework desenvolvido pela google, que tem como objetivo facilitar o processo de cominicação entre sistemas de uma forma extremamente
rapida, leve e independente de linguagem.

- Faz parte da CNCF (Cloud Native Computation Foundation)

## Onde aplicar?

- Ideal para microservicos, comunicações entre eles por exemplo.
- Mobile, Browser e Backend.
- Geração das bibliotecas automaticas (utiliza contratos).
- Streaming bidirecional utilizando HTTP/2 (Server x Cliete em uma unica conexao)


## Protocol Buffers

- É uma linguagem neutra que o google criou, extensível para serialização e desserialização de dados (Binário) ou seja mais leve, rapido e simples.

https://developers.google.com/protocol-buffers



## Protocol Buffers x JSON

- Arquivos binarios são menores que json.
- Processo de serialização do PB é mais leve.
- Gasta menos recurso de rede.
- Processo mais veloz.

search.proto

    syntax = "proto3";
    
    message SearchRequest {
      string query = 1;
      int32 page_number = 2;
      int32 result_per_page = 3;
    }

## HTTP/2

- Nome original SPDY
- Lançado em 2015
- Dados trafegados são binários
- Utiliza a mesma conexão TCP para enviar e receber dados (Multiplex)
- Server Push
- Header comprimidos
- menos recurso de rede
- mais veloz

# gRPC - API "unary" (mesma conexão)

    Cliente ---- Request ---> Server
    Cliente <--- Response --- Server


# gRPC - API "Server streaming" (mesma conexão)

    Cliente ---- Request ---> Server
    Cliente <--- Response --- Server
    Cliente <--- Response --- Server
    Cliente <--- Response --- Server


# gRPC - API "Client streaming" (mesma conexão)

    Cliente ---- Request ---> Server
    Cliente ---- Request ---> Server
    Cliente ---- Request ---> Server
    Cliente <--- Response --- Server



# gRPC - API "Bi derrectional streaming" (mesma conexão)

    Cliente ---- Request ---> Server
    Cliente ---- Request ---> Server
    Cliente ---- Request ---> Server
    Cliente <--- Response --- Server
    Cliente <--- Response --- Server
    Cliente <--- Response --- Server

*podendo ser assincrono

# gRPC X Rest

REST
- Texy/JSON
- Unidericional
- Alta latência (trafegar informações repetidas)
- Sem contrato (maior chance de erro)
- Sem suporte a streaming 
- Design pré-definido
- Bibliotecas de terceiro


gRPC https://grpc.io/docs/languages/kotlin/quickstart/
- Protocol Buffers
- Bidirecional e assincrono
- Baixa latência
- contrato pré definido (.proto)
- Suporte a streaming
- Design livre
- geração de código



## .proto

    message Person {
        int32 id = 1; //inteiro simples
        google.protobuf.Any detail = 2; //sem tipo definido
        google.protobuf.Value data = 3; //tipo dinamicamente setado
        google.protobuf.Timestamp start = 4;//DateTime
        google.protobuf.Duration duration = 5;//TimeSpan
        google.protobuf.Int32Value age = 6;// Int que pode ser null mesmo padrao segue para os outros tipo como StringValue FloatValue
        repeated string roles = 8; //lista de strings
        map<string, string> attributes = 9; //map chave/valor
        oneof result { //ira retornar uma Person ou um Error, caso null ira dispara um exception
            Error error = 10;
            Person person = 11;
        }
    }












































