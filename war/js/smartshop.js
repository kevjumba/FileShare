function loadPosts(containerId) {
    $.ajax({
        async: true,
        url: "/jello/data/app/Post",
        success: function (response) {
        	var post = response.d.results;
        	var postsContainer = $("#"+containerId);
        	for(var i in post) {
        		var p = post[i];
        		postsContainer.append( getPostHtml(p) );
        	}
        }
    });
}

function refreshPost(PostId) {
    $.ajax({
        async: true,
        url: "/jello/view/app/Post("+PostId+")",
        success: function (response) {
        	$("#Post_"+PostId).replaceWith( getPostHtml(response.d) );
        }
    });
}


function getPostHtml(p) {

	return html
}

function buy(PostId) {
	$.ajax({
        async: false,
        url: "/jello/data/app/Post("+PostId+")/buy()",
        success: function (response) {
        	refreshPost(PostId);
        	alert(response);
        }
    });
	
}

var userInfo = {isAdmin:false};
function initUserInfo(containerId) {
	$.ajax({
		url: "/jello/data/jello.services/UserProfile/whoAmI()",
	}).done(function(response) { 
		userInfo = response.d.result;
		if(userInfo.user) {
			$("#"+containerId).html('Welcome <i>' + userInfo.user + '</i> | <a href="' + userInfo.logoutLink + '"><span style="color: #1485c3;">Logout</span></a>');
		}
		else {
			$("#"+containerId).html('Welcome  <i>Guest</i> | <a href="' + userInfo.loginLink + '"><span style="color: #1485c3;">Login</span></a>');	
		}
	});	
}

function updateUserInfo(){
	$.ajax({
		url: "/jello/data/jello.services/UserProfile/whoAmI()",
	}).done(function(response) { 
		userInfo = response.d.result;
	});	
}


function loadButtons(containerId){
	$.ajax({
		url: "/jello/data/jello.services/UserProfile/whoAmI()",
	}).done(function(response) { 
		userInfo = response.d.result;
		var buttonsContainer=$("#"+containerId);;
		buttonsContainer.append('<li><a href="/"><img src="/assets/Cart.png" width="28px"></a></li>\n');
		if(userInfo.user) {
			buttonsContainer.append('<li><a href="jello/view/app/Message()">Message</a></li>\n' +
				     '<li><a href="jello/view/app/Post()">Post</a></li>\n' +
				     '<li><a href="jello/view/app/Message">Messages</a></li>\n');
		}
	});	
}

function createUserIfNeeded(){
	$.ajax({
		url: "/jello/data/app/AppUser/createUser()",
	}).done(function(response) {});	
}
