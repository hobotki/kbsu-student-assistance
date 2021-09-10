package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * Поле-расширение для [ViewHolder]
 *
 * Возвращает [Context] у [ViewHolder.itemView]
 *
 * @author Murad Luguev on 01-09-2021
 */
val ViewHolder.viewContext: Context
    get() = itemView.context