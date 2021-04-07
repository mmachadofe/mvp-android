package br.uol.ps.cards.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Card(
   @SerializedName("id")
   val id: Int,
   val title: String,
   val description: String,
   val lastDigits: String,
   val cardBrand: String,
   val layoutAttrs: CardLayout
) : Parcelable