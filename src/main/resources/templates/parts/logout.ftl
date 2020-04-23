<#macro logout>
    <form action="/logout" method="post">
        <input type = "hidden" name = "_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-dark">Sign out</button>
    </form>
</#macro>