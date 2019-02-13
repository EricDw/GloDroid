package net.publicmethod.glodroid.scopes

import kotlinx.coroutines.CoroutineScope

interface IOScope : CoroutineScope
interface WorkerScope : CoroutineScope
interface UIScope : CoroutineScope