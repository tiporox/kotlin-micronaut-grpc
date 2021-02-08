#Istio

##Links

- https://computerworld.com.br/plataformas/istio-tudo-sobre-o-projeto-open-source-do-google-para-kubernetes/ - overview sobre Istio
- https://istio.io/latest/docs/examples/bookinfo/ 
- https://learn.openshift.com/servicemesh - explicação usando openshift
- https://www.youtube.com/watch?v=Qk7FFBby43U - Tutorial como configurar o istio na aws com kubernetes, começa nos 20min
- https://www.linuxtips.io/loja - Curso pago em português

## O que é?

É uma ferramenta para gerenciar microserviços em termos de observability, segurança e balanceamento, sem necessidade de alterações na aplicação.

##Addons do Istio

- Prometheus: pode ser utilizado com Istio grravar metricas de rastreio da saúde do Istio e das aplicações na malha de serviço.
- Grafana: pode ser usado para monitorar a saúde do Istio e das aplicações na malha de serviço.
- Kiali: auxilia a monitorar a saúdee e entender sua estrutura de malha de serviço.
- Jaeger: auxilia no rastreio end-to-end do sistema, permitindo usuário monitorar e rastrear transações complexas de ponta a ponta. 
- Zipkin: sistema distribuido de rastreio.
- Prometheus Operator: gerencia e operar instancia de prometheus.

##Estrutura

![Estrutura Istio](https://istio.io/latest/docs/examples/bookinfo/withistio.svg)

