package com.eligon.widget.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "Record")
data class Record(
    @field:Attribute(name = "Date")
    @param:Attribute(name = "Date")
    val date: String,

    @field:Attribute(name = "Code")
    @param:Attribute(name = "Code")
    val code: String,

    @Root(name = "Buy")
    val buy: Int,

    @Root(name = "Sell")
    val sell: Int,
)
