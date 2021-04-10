package com.wmm.commonutil

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri

/**
 * ContextProvider 的 onCreate 调用时机在 Application 的 attachBaseContext 和 onCreate 之间
 * 在此时直接赋值context
 */

lateinit var ctx: Context

class CommonInitProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        context?.let {
            ctx = it
        }
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?,
                       selectionArgs: Array<out String>?, sortOrder: String?): Cursor? = null


    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun getType(uri: Uri): String? = null
}