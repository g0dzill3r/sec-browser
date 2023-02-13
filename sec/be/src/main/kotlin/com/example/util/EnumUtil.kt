package com.example.util

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.jvm.javaType

object EnumUtil {
    fun getEnumValues (enumClass: KClass<out Enum<*>>): Array<out Enum<*>> = enumClass.java.enumConstants

    fun isEnum(kClass: KClass<out Any>): Boolean {
        val kType: KType = kClass::class.createType()
        return (kType.javaType  as Class<*>).isEnum
    }
}


// EOF