package com.dndmanager.domain

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "classes")
open class Class(
    //TODO Abilities specific to class ClassAbilitySpecific (Class specifies abilities like rage with Barbarians)
    //TODO Bonus

    @Column(nullable = false)
    var name: String,

    var image: String?,

    @Column(columnDefinition = "TEXT")
    var description: String?,

    @Column(name = "hit_point_die", nullable = false)
    var hitPointDie: Short
) : BaseEntity() {

    companion object : PanacheCompanion<Class>

}