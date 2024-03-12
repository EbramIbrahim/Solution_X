package com.example.solutionxarch.login.domain.usecase

data class LoginUseCase(
    val loginWithEmailUC: LoginWithEmailUC,
    val loginWithPhoneUC: LoginWithPhoneUC,
    val loginWithSocialUC: LoginWithSocialUC
)