package com.csoft.gluon.exceptions

class GluonException : RuntimeException {

    constructor(message: String) : super(message)

    constructor(message: String, throwable: Throwable) : super(message, throwable)
}
