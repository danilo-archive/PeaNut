//JUST AN EXAMPLE, PLEASE USE YOUR OWN PICTURE!
var imageAddr = "http://www.kenrockwell.com/contax/images/g2/examples/31120037-5mb.jpg"; 
var downloadSize = 4995374; //bytes
var data = [];
var value = {};
var speedMbps;
var getDate;
var average = 0;
var sum = 0;
var count = 0;

function ShowProgressMessage(msg) {
    if (console) {
        if (typeof msg == "string") {
            console.log(msg);
        } else {
            for (var i = 0; i < msg.length; i++) {
                console.log(msg[i]);
            }
        }
    }
    
    var oProgress = document.getElementById("progress");
    if (oProgress) {
        var actualHTML = (typeof msg == "string") ? msg : msg.join("<br />");
        oProgress.innerHTML = actualHTML;
    }
}

function InitiateSpeedDetection() {
    ShowProgressMessage("Loading the image, please wait...");
    window.setTimeout(MeasureConnectionSpeed, 1);
};    

    if (window.addEventListener) {
        window.addEventListener('load', InitiateSpeedDetection, false);
    } else if (window.attachEvent) {
        window.attachEvent('onload', InitiateSpeedDetection);
    }

setInterval(function(){ MeasureConnectionSpeed(); }, 3000);

function MeasureConnectionSpeed() {
    var startTime, endTime;
    var download = new Image();
    
    download.onload = function () {
        var currentDate = new Date();
        endTime = currentDate.getTime();
        getDate = currentDate.getHours() + ":" + currentDate.getMinutes();
        showResults();
    }
    
    download.onerror = function (err, msg) {
        ShowProgressMessage([
            "Your connection speed is:", 
            0 + " bps", 
            0 + " kbps", 
            0 + " Mbps",
            
        ]);

        value = {"date": getDate, "Mbps": 0};
        data.push(0);
    }
    
    startTime = (new Date()).getTime();
    var cacheBuster = "?nnn=" + startTime;
    download.src = imageAddr + cacheBuster;
    
    function showResults() {
        var duration = (endTime - startTime) / 1000;
        var bitsLoaded = downloadSize * 8;
        var speedBps = (bitsLoaded / duration).toFixed(2);
        var speedKbps = (speedBps / 1024).toFixed(2);
        speedMbps = parseFloat((speedKbps / 1024).toFixed(2));
        sum += speedMbps;
        count++;
        console.log(sum)
        average = sum / count;
        ShowProgressMessage([
            "Your connection speed is:", 
            speedBps + " bps", 
            speedKbps + " kbps", 
            speedMbps + " Mbps",
            "Download average: " + parseFloat(average.toFixed(2)) + "Mbps"
        ]);
       // value = {"date": getDate, "bps": speedBps};
       // data.push(value);
       // value = {"date": getDate, "kbps": speedKbps};
       // data.push(value);
        value = {"date": getDate, "Mbps": speedMbps};
        data.push(speedMbps);
    }

}