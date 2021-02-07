package br.com.tiporox.users.service

interface Counter {

    var count: Int
    fun getValue(): Int
    fun increment(): Int
}