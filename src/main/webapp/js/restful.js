// Implement ajax request using XMLHttpRequest
var ajaxCall = (function() {
    
        return function(Method, Url, Headers, Body, successFunc, errorFunc) {
            
            var xmlHttpRequest = (window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsotf.XMLHTTP"));
            
            xmlHttpRequest.onreadystatechange = function() {
                if (xmlHttpRequest.readyState == 4 ) {
                    var data = JSON.parse(xmlHttpRequest.responseText);
                    if (xmlHttpRequest.status == 200) {
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
            
            if (Method == 'GET') {
                xmlHttpRequest.send();
            } else {
                xmlHttpRequest.send(JSON.stringify(Body));
            }
            
        };
        
    }

)();

var JSON = JSON || {};

// Implement JSON.stringify serialization
JSON.stringify = JSON.stringify || function(obj) {
    
    var t = typeof (obj);
    
    if (t != "object" || obj === null) {
        
        // Simple data type
        if (t == "string") {
            obj = '"' + obj + '"';
        }
        
        return String(obj);
        
    } else {
        
        // Recurse array or object
        var v, json = [], arr = (obj && obj.constructor == Array);
        
        for (n in obj) {
            v = obj[n];
            t = typeof (v);
            if (t == "string") {
                v = '"' + v + '"';
            } else if (t == "object" && v !== null) {
                v = JSON.stringify(v);
            }
            json.push((arr ? "" : '"' + n + '":') + String(v));
        }
        
        return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
    
    }
    
};

// Implement JSON.parse de-serialization
JSON.parse = JSON.parse || function(str) {
    
    if (str === "") {
        str = '""';
    }
    
    eval("var p=" + str + ";");
    
    return p;

};