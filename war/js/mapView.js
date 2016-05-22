function initMap(containerId) {
	var view;
	$.ajax({
		url: '/jello/data/app/Purchase?$expand=product'
	})
	.fail(function (response) {
		alert(response.responseText);
	})
  	.done(function (response) {
	  	if(response.error) {
			alert(response.error);
			return;
		}
  		var data = response.d.results;
  		view = new mapView(containerId, data);
  		view.setData(data);
  	});	
}

function mapView( containerId, data ) {

	this.container =  $('#' + containerId);
	var that = this;
	
	this.container.append( $('<div id="map" style="height:100%;"></div>') );

	var markers = {};
	var map;
	
	this.setData = function(results) {	  		
  		var bb = boundingBbox(results);
		
		map = new google.maps.Map(document.getElementById('map'), {
		    center: bb.center
		});
		map.fitBounds(bb.bounds);

  		for(var i in results) {
  			var r = results[i];
  			var pos = {lat: r.latitude, lng: r.longitude};
  			
  		  	var image = {
  			  	url: "/jello/data/app/Product("+r.product.serialNumber+")/photo/$value",
  			  	scaledSize: new google.maps.Size(50, 40),
				anchor: new google.maps.Point(23, 87)
		  	};
  		  	
  		  	var iconMask = {
  		  		url: "/assets/map_icon_mask.png",
  		  		scaledSize: new google.maps.Size(75, 106)
  		  	}
  			
			var markerMask = new google.maps.Marker({
				position: pos,
				map: map,
				icon: iconMask,
			  	zIndex: 2*i+1
			});
  		  	
			var marker = new google.maps.Marker({
				position: pos,
				map: map,
				icon: image,
			  	title: r.product.name,
			  	zIndex: 2*i
			});
			
			markers[r.__metadata.uri] = marker;
			
	        var contentString = 
	             '<div id="content"><iframe src="'+r.__metadata.uri.replace("/data/", "/view/") +
	             "?$embeddedRecordDetail=true" +
	             '" width="420" height="510" style="overflow:hidden"></iframe></div>';
	          
	          var infowindow = new google.maps.InfoWindow({
	             content: contentString
	          });
	          
	          markerMask.addListener('click', (function() {
	             var m = markerMask;
	             var infoW = infowindow;
	             return function() {
	                infoW.open(map, m);
	             };
	          })());
	            
  		}		
	}
	
	function boundingBbox(results) {
		var minLat = 1000;
		var minLng = 1000;
		var maxLat = -1000;
		var maxLng = -1000;
		for(var i in results) {
			var lat = results[i].latitude;
			var lng = results[i].longitude;
			if(lat > maxLat)
				maxLat = lat;
    		if(lng > maxLng)
    			maxLng = lng;
    		if(lat < minLat)
    			minLat = lat;
        	if(lng < minLng)
        		minLng = lng;	
		}
		
		var center = {lat: (maxLat+minLat)/2, lng: (maxLng+minLng)/2};
		var southWest = new google.maps.LatLng(minLat - 0.2*(maxLat-minLat) ,minLng - 0.2*(maxLng-minLng));
		var northEast = new google.maps.LatLng(maxLat + 0.2*(maxLat-minLat), maxLng + 0.2*(maxLng-minLng));
		var bounds = new google.maps.LatLngBounds(southWest,northEast);
	    		 
		return {center:center, bounds:bounds}; 
	}
}
    
