<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/userRoom.ftl" as userRoom>
<#import "parts/identityDocumentShow.ftl" as docShow>
<@common.commonPage>
<@userRoom.userPage>

    <h3>Current identity document</h3>
    <div>
        <#if currentPassport??>
            <@docShow.showDocument currentPassport/>
        <#else>
            <div>Identity document is not create!</div>
        </#if>

        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Set an identity document
        </a>

        <div class="collapse <#if docIsInvalid??>show</#if>  mt-3" id="collapseExample">

            <form action="/myUser/setDocument" method="post">
                <table class="table col-8">
                    <tr>
                        <td>Name of document : </td>
                        <td colspan="3">
                            <div>Паспорт РБ</div>
                        </td>
                    </tr>

                    <tr>
                        <td>Series : </td>
                        <td colspan="3">
                            <input class="form-control ${(seriesError??)?string('is-invalid','')}" type="text" name = "series" value="<#if passport??>${passport.series!}</#if>" />
                            <#if seriesError??>
                                <div class="invalid-feedback">
                                    ${seriesError}
                                </div>
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td>Number : </td>
                        <td colspan="3">
                            <input class="form-control ${(numberError??)?string('is-invalid','')}" type="text" value="<#if passport??>${passport.number!}</#if>" name = "number"/>
                            <#if numberError??>
                                <div class="invalid-feedback">
                                    ${numberError}
                                </div>
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td>Your name : </td>
                        <td colspan="3">
                            <input class="form-control ${(firstNameError??)?string('is-invalid','')}" type="text" value="<#if passport??>${passport.firstName!}</#if>" name = "firstName"/>
                            <#if firstNameError??>
                                <div class="invalid-feedback">
                                    ${firstNameError}
                                </div>
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <td>Your surname : </td>
                        <td colspan="3">
                            <input class="form-control ${(lastNameError??)?string('is-invalid','')}" type="text" value="<#if passport??>${passport.lastName!}</#if>" name = "lastName"/>
                            <#if lastNameError??>
                                <div class="invalid-feedback">
                                    ${lastNameError}
                                </div>
                            </#if>
                        </td>
                    </tr>
                </table>

                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="col mt-3"><button type="submit" class="btn btn-dark">Set</button></div>
            </form>
        </div>
    </div>
</@userRoom.userPage>
</@common.commonPage>