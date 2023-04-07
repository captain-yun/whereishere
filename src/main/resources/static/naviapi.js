    // HTML5의 geolocation 으로 사용할 수 있는지 확인합니다
    // GeoLocation 을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function (position) {
    let lat = position.coords.latitude;     // 위도
    let lon = position.coords.longitude;    // 경도
    let point = [];
    point[0] = lat;
    point[1] = lon;


    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
    center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

    // 지도를 생성합니다
    let map = new kakao.maps.Map(mapContainer, mapOption);

    // 지도에 표시된 마커 객체를 가지고 있을 배열입니다

    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function (result, status) {

    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {

    var coords = new kakao.maps.LatLng(lat, lon);

    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
    map: map,
    position: coords
});

    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
    content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
});
    infowindow.open(map, marker);
}
});

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function (result, status) {

    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {

    var coords = new kakao.maps.LatLng(lat+0.001, lon+0.001);

    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
    map: map,
    position: coords
});

    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
    content: '<div style="width:150px;text-align:center;padding:6px 0;">느그회사</div>'
});
    infowindow.open(map, marker);
}
});

    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(new kakao.maps.LatLng(lat, lon));
});
