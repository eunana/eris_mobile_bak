//sw.js 파일
//const CACHE_NAME = 'eGovMobile'; 

var CACHE_STATIC_NAME = 'static-v3';
var CACHE_DYNAMIC_NAME = 'dynamic-v3';

self.addEventListener('install', event => {
	console.log('Service worker installing...');
	event.waitUntil(
		caches.open(CACHE_STATIC_NAME)
		.then(function(cache) {
			console.log('precaching app shell');
			cache.addAll([
				'/',
				'/js/index.js',
				'/js/fetch.js',
				'/js/promise.js'
				]);
		})
	);
});

self.addEventListener('activate', event => {
  console.log('Service worker activating...');
  event.waitUntil(
		  caches.keys()
		  .then(function(keyList) {
			  return Promise.all(keyList.map(function(key) {
				  if (key !== CACHE_STATIC_NAME && key !== CACHE_DYNAMIC_NAME) {
					  console.log('[Service Worker] Removing old cache.', key);
					  return caches.delete(key);
				  }
			  }));
		  })
	);
  return self.clients.claim(); 
});

self.addEventListener("fetch", function(event) {
	console.log("Fetch request for:", event);
	event.respondWith(
		caches.match(event.request)
			.then(function(response){
				if(response) {
					return response;
				} else {
					return fetch(event.request)
					.then(function(res) {
						return caches.open(CACHE_DYNAMIC_NAME)
							.then(function(cache) {
								cache.put(event.request.url, res.clone());
								return res;
							})
					})
					.catch(function(err){
						
					});
				}
			})
	);
});  

/*

//'install' 이벤트를 리스닝하며, 사이트 자산(assets)을 캐싱
self.addEventListener('install', function(e) {
	e.waitUntil(
		caches.open(CACHE_NAME).then(function(cache) {
			return cache.addAll([
				'/',
				'/index.html',
				'/js/index.js',
				'/images/icons/icon-72x72.png',
				'/images/icons/icon-96x96.png',
				'/images/icons/icon-128x128.png',
				'/images/icons/icon-144x144.png',
				'/images/icons/icon-152x152.png',
				'/images/icons/icon-196x196.png',
				'/images/icons/icon-384x384.png',
				'/images/icons/icon-512x512.png'
				// 필요한 자산(assets)들을 여기에 넣으세요
			]);
		})
	);
});



//'fetch' 요청을 리스닝하며, 캐시에서 발견될 경우 캐시로부터 파일을 가져옴
self.addEventListener('fetch', function(event) {
	event.respondWith(
		caches.match(event.request).then(function(response) {
			return response || fetch(event.request);
		})
	);
});


self.addEventListener('install', function(event) {
	console.log('[Service Worker] Installing Service Worker ...', event);

	event.waitUntil (
		caches.open('static').then(function(cache) {
			cache.addAll(['/', '/index.html', '/manifest.json']);
		})
	);
});

self.addEventListener('activate', function(event) {
	console.log('[Service Worker] Activating Service Worker ....', event);
});

self.addEventListener('fetch', function(event) {
	event.respondWith(
		caches.match(event.request).then(function(response) {
			if (response) {
				return response;
			} else {
				return fetch(event.request).then(function(res) {
					return caches.open('dynamic').then(function(cache) {
						cache.put(event.request.url, res.clone());
						return res;
					});
				});
			}
		})
	);
});
*/
