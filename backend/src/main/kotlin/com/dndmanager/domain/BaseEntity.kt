package com.dndmanager.domain

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity

abstract class BaseEntity : PanacheEntity() { open fun isTrusted(sub: String) = false }