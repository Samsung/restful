var HOST = "";
var LOGIN_PAGE = "login.html";
var JOIN_PAGE = "join.html";
var MAIN_PAGE = "main.html";
var ME_PAGE = "me.html";

//window.onload
window.onload = function(){
    init(); 
};

//initializer
function init(){
    var url = getdefaultUrl();
    var params = {};
    if(!url) return;
    
    ajaxCall('GET', url, null, params,
        function(data){
            var status = data.status;
            if(status == 'OK'){
                successInit(data);
            }       
    },  function(data){
            failInit(data);
    });
}

//get Current URL
function getCurUrl(){
    var curUrl = window.location.href;
    var cPage = curUrl.split('/jsp/')[1];
    return cPage;
}

//get default URL
function getdefaultUrl(){
    switch(getCurUrl()) {
     case LOGIN_PAGE :
     case MAIN_PAGE :
     case ME_PAGE :{
         return '../api/user/me';
         break;
     }
     case JOIN_PAGE :
         return null;
         break;
     }            
}

//sucess function for init
function successInit(data){
     switch(getCurUrl()) {
     case LOGIN_PAGE :{
         location.replace(MAIN_PAGE);
         break;
     }
     case ME_PAGE :    { 
         var html = "";
         for (s in data.response) {              
             html += s + " : " + data.response[s] + "<br/>";
         }
         document.getElementById('me').innerHTML = html;
         break;
     }
     case MAIN_PAGE :
     case JOIN_PAGE :
     }            
}

//fail function for init
function failInit(data){
     switch(getCurUrl()) {
     case MAIN_PAGE : 
         location.replace(LOGIN_PAGE);
         break;
     case ME_PAGE :     
     case JOIN_PAGE :
     case LOGIN_PAGE :
         break;
     }            
}

//Login
function login(){
    
    var url = "../api/user/login";
    
    var username =  document.getElementById('login_text').value;
    var password =  document.getElementById('login_password').value;
    
    if (!username || !password) {
        alert('Please insert your userid and password');
        return;
    }
    
    var params = {
        'name' : username,
        'pwd' : password
    };
    
    ajaxCall('POST', url, null, params, 
        function(data) {
            var status = data.status;
            if(status == 'OK'){
                location.replace(MAIN_PAGE);
            }
        },
        function(jqXHR){
            errorfunctions(jqXHR);
      });
}

//Open join window 
function joinWindow(){
    windowOpen(JOIN_PAGE, 390, 300);
}

//Move me page
function meWindow(){
	location.replace(ME_PAGE);
}

//open window
function windowOpen(url, width, height){
    var left = Math.ceil((window.screen.width - width)/2);
    var top = Math.ceil((window.screen.height - height)/2);
    window.open(url,"new","top="+top+" left="+left+" width=390, height=300, resizable=no, scrollbars=no, status=no, location=no, directories=no;");
}

//Join
function join(){
    var url = "../api/user/create";
    
    var userid =  document.getElementById('join_text').value;
    var username =  document.getElementById('join_username').value;
    var password =  document.getElementById('join_password').value;
    var password_confirm =  document.getElementById('join_password_confirm').value;
    
    if (!userid || !username || !password || !password_confirm) {
        alert('Please fill all the blanks.');
        return;
    }
    
    if(password != password_confirm){
        alert('Please check your password.');
        return;
    }
    
    var params = {
        'name' : username,
        'pwd' : password,
        'username' : username,
        'role': 'User'
    };
    
    ajaxCall('POST', url, null, params, 
        function(data) {
            var status = data.status;
            if(status == 'OK'){
                var join = [ 'join_text', 'join_username', 'join_password', 'join_password_confirm' ];
                for (o in join) {
                    document.getElementById(join[o]).value = '';
                }
                alert('Join successfully');
                window.close();
            }
        },
        function(jqXHR){
            errorfunctions(jqXHR);
      });
}

//Logout
function logout(){
    
    var url = "../api/user/logout";
    
    ajaxCall('GET', url, null, null, 
        function(data) {
            var status = data.status;
            if(status == 'OK'){
                if(location.href != LOGIN_PAGE){
                    location.replace(LOGIN_PAGE);
                }
            }
        },
        function(jqXHR){
            errorfunctions(jqXHR);
      });
}


function createTable(tagId, data){
     var obj = data.response.object;
     for (o in obj) {
         var html = obj[o];
         for (v in html) {
             var key = v;
             var value = html[v];
             document.getElementById(tagId).innerHTML += key + ' : ' + value + '<br/>';
         }
         document.getElementById(tagId).innerHTML += '<br/>';
     }
}

//error function
function errorfunctions(jqXHR){
    var status = jqXHR.status;
    var response = jqXHR.response;
    if(status != 'OK') alert(response);
}

//implement ajax request using XMLHttpRequest
var ajaxCall = (function(){
    return function(Method, Url, Headers, Body, successFunc, errorFunc) {
        
        var xmlHttpRequest = ( window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsotf.XMLHTTP"));
        
        xmlHttpRequest.onreadystatechange = function() {
            if (xmlHttpRequest.readyState == 4 ) {
                var data = JSON.parse(xmlHttpRequest.responseText);
               if(xmlHttpRequest.status == 200) {
                    successFunc(data);
               } else {
                    errorFunc(data);
               }
            }
        };
        
        xmlHttpRequest.open(Method, Url, true);
        xmlHttpRequest.setRequestHeader('Content-type', 'application/json;charset=utf-8');
        xmlHttpRequest.setRequestHeader("Cache-Control","no-cache, must-revalidate");
        xmlHttpRequest.setRequestHeader("Pragma","no-cache");
        
        if(Method == 'POST'){
            xmlHttpRequest.send(JSON.stringify(Body));
        } else {
            xmlHttpRequest.send();
        }
    };
})();

var JSON = JSON || {};

// implement JSON.stringify serialization
JSON.stringify = JSON.stringify || function(obj) {

    var t = typeof (obj);
    if (t != "object" || obj === null) {

        // simple data type
        if (t == "string")
            obj = '"' + obj + '"';
        return String(obj);

    } else {
        // recurse array or object
        var n, v, json = [], arr = (obj && obj.constructor == Array);

        for (n in obj) {
            v = obj[n];
            t = typeof (v);

            if (t == "string")
                v = '"' + v + '"';
            else if (t == "object" && v !== null)
                v = JSON.stringify(v);

            json.push((arr ? "" : '"' + n + '":') + String(v));
        }
        return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
    }
};

// implement JSON.parse de-serialization
JSON.parse = JSON.parse || function(str) {
    
    if (str === "")
        str = '""';
    eval("var p=" + str + ";");
    return p;
};