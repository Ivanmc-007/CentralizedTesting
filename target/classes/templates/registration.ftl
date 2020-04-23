<#import "parts/common.ftl" as common>
<@common.commonPage>
    <div class="col"><h3>Registration</h3></div>

    <#if messageSuccessfullyRegistration??>
        <div class="alert alert-success" role="alert">
            ${messageSuccessfullyRegistration}
        </div>
    <#else>

    <div>
        <form method="post" action="/registration">
            <div class="col-5 mt-3">
                <input class="form-control ${(loginError??)?string('is-invalid','')}" type="text" name="login" value="<#if myUser??>${myUser.login!}</#if>" placeholder="Login" />
                <#if loginError??>
                    <div class="invalid-feedback">
                        ${loginError}
                    </div>
                </#if>
            </div>
            <div class="col-5 mt-3">
                <input class="form-control ${(passwordError??)?string('is-invalid','')}" type="password" name="password" placeholder="Password" />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
            <div class="col-5 mt-3">
                <input class="form-control ${(password2Error??)?string('is-invalid','')}" type="password" name="password2" placeholder="Repeat password" />
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
            </div>
            <div class="col-5 mt-3">
                <input class="form-control ${(emailError??)?string('is-invalid','')}" type="email" name="email" value="<#if myUser??>${myUser.email!}</#if>" placeholder="mail@mail.com" />
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="col mt-3"><button type="submit" class="btn btn-dark">Sign Up</button></div>
        </form>
    </div>
    </#if>
</@common.commonPage>