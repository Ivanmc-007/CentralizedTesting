<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<@common.commonPage>
    <@adminRoom.adminPage>

        <#if choice??>
        <form action="/admin/choices/${choice.id}" class="col" method="post">

            <div>PERMISSION № <#if permission??><label class="font-weight-bold">${permission.id}</label></#if></div>

            <div>Testing Point : <label class="font-weight-bold">${choice.university.name!}</label></div>
            <table class="table table-sm col">
                <tr>
                    <td>Surname : </td>
                    <td colspan="3"><label class="font-weight-bold">${choice.myUser.passport.lastName!}</label></td>
                </tr>
                <tr>
                    <td>Name : </td>
                    <td colspan="3"><label class="font-weight-bold">${choice.myUser.passport.firstName}</label></td>
                </tr>
                <tr>
                    <td>Identity document : </td>
                    <td colspan="3"><label class="font-weight-bold">Паспорт РБ</label></td>
                </tr>
                <tr>
                    <td>Series : </td>
                    <td colspan="3"><label class="font-weight-bold">${choice.myUser.passport.series}</label></td>
                </tr>
                <tr>
                    <td>Number : </td>
                    <td colspan="3"><label class="font-weight-bold">${choice.myUser.passport.number}</label></td>
                </tr>
                <tr>
                    <td>Answer form number : </td>
                    <td>_________________</td>
                </tr>
                <tr>
                    <td>Subject : </td>
                    <td><label class="font-weight-bold">${choice.subject.name!}</label></td>
                </tr>
                <tr>
                    <td>Language : </td>
                    <td>
                        <input class="form-control ${(languageError??)?string('is-invalid','')}" type="text" name="language" <#if permission??>value="${permission.language!}"</#if>>
                        <#if languageError??>
                            <div class="invalid-feedback">
                                ${languageError}
                            </div>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td>Date : </td>
                    <td>
                        <input class="form-control ${(dateError??)?string('is-invalid','')}" type="date" name="date", value="${dateStartTest!}">
                        <#if dateError??>
                            <div class="invalid-feedback">
                                ${dateError}
                            </div>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td>Time : </td>
                    <td>
                        <input class="form-control ${(timeError??)?string('is-invalid','')}" type="time" name="time" value="${timeStartTest!}">
                        <#if timeError??>
                            <div class="invalid-feedback">
                                ${timeError}
                            </div>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td>Test point address : </td>
                    <td><input type="text" class="form-control" name="address" <#if permission??>value="${permission.address!}"</#if>></td>
                </tr>
                <tr>
                    <td>Проезд : </td>
                    <td><textarea class="form-control"  rows="3" name="wayInformation"><#if permission??>${permission.wayInformation!}</#if></textarea></td>
                </tr>

            </table>

            <div>С правилами участия в ЦТ ознакомлен(а). Правильность данных подтверждаю.</div>
            <div>_______ ${choice.myUser.passport.lastName!} ${choice.myUser.passport.firstName} __ _______ 20__ г.</div>

            <div>Уполномоченный представитель пункта регистрации.</div>
            <div>_______ ____________________ __ _______ 20__ г.</div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="col mt-3"><button type="submit" class="btn btn-dark">Accept</button></div>
        </form>
        </#if>
    </@adminRoom.adminPage>
</@common.commonPage>