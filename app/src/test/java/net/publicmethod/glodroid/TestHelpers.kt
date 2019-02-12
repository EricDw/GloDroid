package net.publicmethod.glodroid

val generateValidPersonalAuthenticationToken = {
    var result = ""
    repeat((0..VALID_PAT_LENGTH).count()) { result += 1 }
    result
}