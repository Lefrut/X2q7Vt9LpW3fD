package com.effective.home.ui

import android.content.Context
import android.util.TypedValue
import com.effective.resources.R
import com.effective.ui.res.getAttrColor
import com.google.android.material.R as MaterialRes

enum class FastFilterIcon {
    NearVacancies,
    LevelUpResume,
    TemporaryJob,
    None
}

fun getFastFilterIcon(id: String): FastFilterIcon {
    return when (id) {
        "near_vacancies" -> {
            FastFilterIcon.NearVacancies
        }

        "level_up_resume" -> {
            FastFilterIcon.LevelUpResume

        }

        "temporary_job" -> {
            FastFilterIcon.TemporaryJob
        }

        else -> {
            FastFilterIcon.None
        }
    }
}


fun FastFilterIcon.getResId() = when (this) {
    FastFilterIcon.NearVacancies -> {
        R.drawable.place_24
    }

    FastFilterIcon.LevelUpResume -> {
        R.drawable.star_24
    }

    FastFilterIcon.TemporaryJob -> {
        R.drawable.list_done_24
    }

    FastFilterIcon.None -> {
        null
    }
}

fun Int?.getPairColorInt(context: Context) = when (this) {
    R.drawable.place_24 -> {
        getAttrColor(
            context,
            MaterialRes.attr.colorPrimary,
            MaterialRes.attr.colorPrimaryDark
        )
    }

    R.drawable.star_24 -> {
        getAttrColor(
            context,
            MaterialRes.attr.colorSecondary,
            MaterialRes.attr.colorSecondaryVariant
        )
    }

    R.drawable.list_done_24 -> {
        getAttrColor(
            context,
            MaterialRes.attr.colorSecondary,
            MaterialRes.attr.colorSecondaryVariant
        )

    }

    else -> {
        getAttrColor(
            context,
            MaterialRes.attr.colorSecondary,
            MaterialRes.attr.colorSecondaryVariant
        )
    }
}