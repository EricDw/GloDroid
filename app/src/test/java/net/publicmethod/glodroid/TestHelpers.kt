package net.publicmethod.glodroid

val generateValidPersonalAuthenticationTokenString = {
    var result = ""
    repeat((0..VALID_PAT_LENGTH).count()) { result += 1 }
    result
}