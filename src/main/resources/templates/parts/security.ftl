<#assign    logged_in = Session.SPRING_SECURITY_CONTEXT??>

<#if logged_in>
    <#assign
            user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
            username = user.getUsername()
            isAdmin = user.isAdmin()
    >

</#if>
