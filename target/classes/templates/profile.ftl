<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>
<@common.commonPage>

    <div class="col"><h3>Change Password or/and Email</h3></div>
    <#if messageUpdate??>
        <div class="alert alert-success" role="alert">
            ${messageUpdate}
        </div>
    </#if>

    <form action="/myUser/profile" method="post">
        <div class="col-5 mt-3">
            <input class="form-control ${(passwordError??)?string('is-invalid','')}" type="password" name="password" placeholder="Current password"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>

        <div class="col-5 mt-3">
            <input class="form-control ${(newPasswordError??)?string('is-invalid','')}" type="password" name="newPassword" placeholder="New password"/>
            <#if newPasswordError??>
                <div class="invalid-feedback">
                    ${newPasswordError}
                </div>
            </#if>
        </div>

        <div class="col-5 mt-3"><input class="form-control ${(emailError??)?string('is-invalid','')}" type="email" name="newEmail" value="${currEmail!}"/>
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="col mt-3"><button type="submit" class="btn btn-dark">Accept</button></div>
    </form>

</@common.commonPage>