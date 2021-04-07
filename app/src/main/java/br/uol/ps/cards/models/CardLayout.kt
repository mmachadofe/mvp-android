package br.uol.ps.cards.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardLayout(
    val bgColor: String,
    val titleColor: String
) : Parcelable
