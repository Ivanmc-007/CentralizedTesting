<#macro showDocument identityDocument>
    <#if identityDocument??>
        <table class="table col-8">
            <tr>
                <td>Name of document : </td>
                <td colspan="3">Passport RB</td>
            </tr>
            <tr>
                <td>Series : </td>
                <td colspan="3">${identityDocument.series!}</td>
            </tr>
            <tr>
                <td>Number : </td>
                <td colspan="3">${identityDocument.number!}</td>
            </tr>
            <tr>
                <td>Your name : </td>
                <td colspan="3">${identityDocument.firstName!}</td>
            </tr>
            <tr>
                <td>Your surname : </td>
                <td colspan="3">${identityDocument.lastName!}</td>
            </tr>
        </table>
    </#if>
</#macro>