package com.express.android.mitchnavigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Money(val amount: BigDecimal): Parcelable {

}
