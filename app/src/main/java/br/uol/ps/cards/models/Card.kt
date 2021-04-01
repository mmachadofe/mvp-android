package br.uol.ps.cards.models

import com.google.gson.annotations.SerializedName

data class Card(
   @SerializedName("id")
   val id: Int,
   val title: String,
   val description: String,
   val lastDigits: String,
   val cardBrand: String,
   val layoutAttrs: CardLayout
)