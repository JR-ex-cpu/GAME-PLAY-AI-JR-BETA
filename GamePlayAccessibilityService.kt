package com.jr.gameplayai

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import java.util.concurrent.ConcurrentLinkedDeque

class GamePlayAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
        Log.i(TAG, "BOT JR AccessibilityService conectado")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return

        val packageName = event.packageName?.toString() ?: "desconocido"
        val className = event.className?.toString() ?: "desconocido"
        val text = event.text.joinToString(separator = ", ") { it.toString() }

        val line = "type=${event.eventType}, package=$packageName, class=$className, text=$text"
        Log.i(TAG, "Evento: $line")

        events.addFirst(line)
        while (events.size > MAX_EVENTS) events.pollLast()
    }

    override fun onInterrupt() {
        Log.i(TAG, "Servicio interrumpido")
    }

    override fun onDestroy() {
        instance = null
        super.onDestroy()
    }

    companion object {
        private const val TAG = "BOTJR"
        private const val MAX_EVENTS = 50

        @Volatile
        var instance: GamePlayAccessibilityService? = null

        val events = ConcurrentLinkedDeque<String>()
    }
}
