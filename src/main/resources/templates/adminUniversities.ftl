<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<#import "parts/universitiesShow.ftl" as universitiesShow>
<@common.commonPage>
    <@adminRoom.adminPage>

        <@universitiesShow.universitiesShow universities/>

        <h3>Fill in the fields</h3>
        <form method="post" action="/admin/universities/add">
            <div class="form-group">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="text" class="form-control mb-3 ${(universityNameError??)?string('is-invalid','')}" name="universityName" placeholder="University name"/>
                <#if universityNameError??>
                    <div class="invalid-feedback">
                        ${universityNameError}
                    </div>
                </#if>
                <button type="submit" class="btn btn-dark mb-5">Add university</button>
            </div>
        </form>

    </@adminRoom.adminPage>
</@common.commonPage>