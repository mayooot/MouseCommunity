function post() {
    let postId = $("#post_id").val()
    let content = $("#comment_content").val()

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify(  {
            "parentId": postId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            // 如果是200，说明请求成功，隐藏回复框
            if (response.code === 200) {
                $("#comment_section").hide()
            } else {
                if (response.code === 2003) {
                    // 没有登录
                    let isAccepted = confirm(response.message)
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=7d8494545673126c8fa5&redirect_uri=http://localhost/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", true)
                    }
                } else {
                    alert(response.message)
                }
            }
        },
        dataType: "JSON"
    })
}
