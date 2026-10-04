package com.jr.gameplayai

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class GamePlayAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i(TAG, "BOT JR AccessibilityService conectado")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return

        val packageName = event.packageName?.toString() ?: "desconocido"
        val className = event.className?.toString() ?: "desconocido"
        val text = event.text.joinToString(separator = ", ") { it.toString() }

        Log.i(
            TAG,
            "Evento: type=${event.eventType}, package=$packageName, class=$className, text=$text"
        )
    }

    override fun onInterrupt() {
        Log.i(TAG, "Servicio interrumpido")
    }

    companion object {
        private const val TAG = "BOTJR"
    }
}
