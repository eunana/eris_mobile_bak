//메인 html 파일(index.html) 안의 body 태그 끝의 script 태그 안에 아래 코드를 붙여 넣으세요.
//서비스 워커 등록
if ('serviceWorker' in navigator) {
	
	self.addEventListener('load', () => {
		navigator.serviceWorker.register('sw.js')
		.then(reg => {
			console.log('서비스 워커가 등록되었습니다.', reg);
		})
		.catch(err => {
			console.log('Registration failed:', err);
		});
		
		setTimeout(() => {
		    const img = new Image();
		    img.src = 'images/egovframework/eris/call.png';
		    $("#cooperation").after(img);
		  }, 10000);
		
	});
}

var deferredPrompt;

window.addEventListener('beforeinstallprompt', function(event) { // install 배너
	console.log('beforeinstallprompt_event : ',event);
	event.preventDefault();
	event.stopPropagation();
	deferredPrompt = event;
	self.promptEvent = event;
	 
	return false;
});

if(!window.Promise) {
	window.Promise = Promise;
}



/*if ('serviceWorker' in navigator) {
	navigator.serviceWorker.register('/js/sw.js')
			.then(function() {
		console.log("Service Worker Registered");
	});
}*/
/*
var savedPrompt = null;

window.addEventListener('beforeinstallprompt', beforeInstallPrompt);

function beforeInstallPrompt(event) {
	event.preventDefault();
	savedPrompt = event;
	//이곳에 여러분의 어플리케이션을 홈 스크린에 추가하기 위한 UI를 보여주는 로직을 구현하세요 (아마도 버튼이겠죠)
}

navigator.serviceWorker.ready.then(function(registration) {
	// Use the PushManager to get the user's subscription to the push service.
	return registration.pushManager.getSubscription().then(async function(subscription) {
		// If a subscription was found, return it.
		if (subscription) {
			return subscription;
		}

		// Get the server's public key
		const response = await fetch('./vapidPublicKey');
		const vapidPublicKey = await response.text();
		// Chrome doesn't accept the base64-encoded (string) vapidPublicKey yet
		// urlBase64ToUint8Array() is defined in /tools.js
		const convertedVapidKey = urlBase64ToUint8Array(vapidPublicKey);

		// Otherwise, subscribe the user (userVisibleOnly allows to specify that we don't plan to
		// send notifications that don't have a visible effect for the user).
		return registration.pushManager.subscribe({
			userVisibleOnly: true,
			applicationServerKey: convertedVapidKey
		});
	});
}).then(function(subscription) {
	// Send the subscription details to the server using the Fetch API.
	fetch('./register', {
		method: 'post',
		headers: {
			'Content-type': 'application/json'
		},
		body: JSON.stringify({
			subscription: subscription
		}),
	});

	document.getElementById('doIt').onclick = function() {
		const payload = document.getElementById('notification-payload').value;
		const delay = document.getElementById('notification-delay').value;
		const ttl = document.getElementById('notification-ttl').value;

		// Ask the server to send the client a notification (for testing purposes, in actual
		// applications the push notification is likely going to be generated by some event
		// in the server).
		fetch('./sendNotification', {
			method: 'post',
			headers: {
				'Content-type': 'application/json'
			},
			body: JSON.stringify({
				subscription: subscription,
				payload: payload,
				delay: delay,
				ttl: ttl,
			}),
		});
	};
});
*/


/*window.addEventListener("beforeinstallprompt", (ev) => {
	if (isStandalone()) {
		// PWA already installed.
		event.preventDefault();
		return false;
	} else {
		alert("PWA not installed.");
		// PWA not installed.
	}
});

function isStandalone() {
	// Check if device supports service workers
	if (!('serviceWorker' in window.navigator)) return false;

	// Check for Android
	if (window.matchMedia('(display-mode: standalone)').matches) return true;

	// Check for iOS
	if (window.navigator["standalone"] == true) return true;

	return false;
}*/
