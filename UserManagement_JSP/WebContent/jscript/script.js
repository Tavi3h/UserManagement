/**
 * 
 */

// 管理页面删除确认
function delConfirm() {
	return window.confirm("Do you really want to del this user?");
}

// 添加用户确认
function addConfirm() {
	return window.confirm("Do you really want to add this user?");
}

// 更改用户信息确认
function updateConfirm() {
	return window.confirm("Do you really want to update this user?");
}

// 查询表单用户名不能为空
function queryCheck(obj) {
	if (obj.username.value == "") {
		alert("Type in 'username' please ... ");
		return false;
	}
	return true;
}

// 登录表单信息不能为空
function loginCheck(obj) {

	if (obj.username.value == "") {
		alert("Type in 'Username' please ... ");
		return false;
	} else if (obj.password.value == "") {
		alert("Type in 'Password' please ... ");
		return false;
	} else if (obj.vcode.value == "") {
		alert("Type in 'ValidCode' please ... ");
		return false;
	}

	return true;
}

// 实现验证码单击刷新
function refresh() {
	document.getElementById("validcode").src = "ValidCodeServlet?" + new Date().getTime();
}