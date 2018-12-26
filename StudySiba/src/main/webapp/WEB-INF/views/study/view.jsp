<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
    $(document).ready(function() {
        $('#study_contenttext').summernote({
            placeholder: '',
            tabsize: 100,
            height: 150,
            width: 940
        });
        $.datepicker.setDefaults({
            dateFormat: 'yy-mm-dd',
            prevText: '이전 달',
            nextText: '다음 달',
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNames: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            showMonthAfterYear: true,
            yearSuffix: '년'
        });
        $('.datepickter').datepicker();
    });

</script>


<div class="content_subject">
    <div class="content_subjectleft">
        <img src="/images/sub/reading.png">
        <span>스터디룸</span>
    </div>
</div>


<div class="content_room">
    <div class="room_header">
        <div class="roomheader_left">
            <img src="/local_upload/study/${view.fileName }">
        </div>
        <div class="roomheader_right">
        	<input type="hidden" id="room_no" value="${view.no }">
            <div class="form-row roomheader_title">
                <h2>${view.gName }</h2>
            </div>
            <div class="form-row mt-sm-3">
                <div class="form-group col-sm-2 roomheader_img">
                    <img class="rounded-circle" src="/images/profile/kakao/${view.proFile }">
                </div>
                <div class="form-group col-sm-8">
                    <div class="form-row mt-sm-2">
                        스터디장 :
                    </div>
                    <div class="form-row roomheader_leader">
                        <span>${view.nick }</span>
                        <img class="roomheader_message modal_open" id="messageBtn" src="/images/main/mail.png" data="messageModal">
                        <img class="roomheader_message modal_open" id="friendBtn" src="/images/main/friendship.png" data="messageModal">
                    </div>
                </div>
            </div>
            <div class="form-row mt-sm-2 roomheader_button">
                <c:choose>
                	<c:when test="${isGroup eq 'true' }">
                        <button class="btn btn-danger roomheader_group" data="join">참여중</button>
                    </c:when>
                    <c:when test="${view.person le groupCount }">
                        <button class="btn btn-danger roomheader_group" data="close">참여불가</button>
                    </c:when>
                    <c:when test="${isGroup eq 'false' }">
                        <button class="btn btn-primary roomheader_group" data="unjoin">참가신청</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>

    <div class="room_body mt-sm-5">
        <div class="form-row">
            <div class="form-gorup col-sm-8">
                <div class="form-row">
                    <h2>세부사항</h2>
                </div>
                <div class="form-row mt-sm-4">
                    <h4>${view.title }</h4>
                </div>
                <div class="mt-sm-2 pr-sm-3">
                    ${view.content }
                </div>
            </div>

            <div class="form-gorup col-sm-4">
                <div class="form-row roombody_info">
                    <div class="form-row mt-sm-5">
                        <div class="form-row form-control roombody_map" id="room_map">

                        </div>
                        <div class="roombody_period">
                            <div class="form-row">
                                <div class="form-group col-sm-2">
                                    <img src="/images/sub/calendar.png">
                                </div>
                                <div class="form-group col-sm-10 mt-sm-1 roombody_text">
                                    ${view.toPer }~${view.fromPer }
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-sm-2">
                                    <img src="/images/sub/circular-clock.png">
                                </div>
                                <div class="form-group col-sm-10 mt-sm-1 roombody_text">
                                    ${view.toTime }~${view.fromTime }
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-sm-2">
                                    <img src="/images/sub/global.png">
                                </div>
                                <div class="form-group col-sm-10 mt-sm-1 roombody_text">
                                    ${view.address }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="room_footer mt-sm-5">
        <div class="form-row">
            <h4>그룹인원</h4>
        </div>


        <div class="col-sm-2 roomfooter_list">
            <div class="roomfooter_info modal_open" data="messageModal">
                <img class="rounded-circle" src="/images/profile/kakao/${view.proFile }">
                <p>${view.nick }</p>
                <span>스터디장</span>
            </div>
        </div>

        <c:forEach items="${userList}" var="userList">
            <div class="col-sm-2 roomfooter_list">
                <div class="roomfooter_info modal_open" data="messageModal">
                    <img class="rounded-circle" src="/images/profile/kakao/${userList.proFile }">
                    <p>${userList.nick }</p>
                    <span>회원</span>
                </div>
            </div>
        </c:forEach>

    </div>
</div>



<script>
    function initMap() {

        var lat = '${view.lat}';
        var lng = '${view.lng}';


        console.log(lat + ' : ' + lng);

        var origin = {
            lat: lat,
            lng: lng
        };

        var map = new google.maps.Map(document.getElementById('room_map'), {
            zoom: 30,
            center: origin
        });


        var marker = new google.maps.Marker({
            position: origin,
            map: map,
            title: 'Hello World!'
        });

        var clickHandler = new ClickEventHandler(map, origin);
    }

    function initAutocomplete() {

        var lat = '${view.lat}';
        var lng = '${view.lng}';


        var map = new google.maps.Map(document.getElementById('room_map'), {
            center: {
                lat: +lat,
                lng: +lng
            },
            zoom: 15,
            /*  mapTypeId: 'roadmap' */
        });

        var clickHandler = new ClickEventHandler(map, origin);
    }



    /**
     * @constructor
     */
    var ClickEventHandler = function(map, origin) {
        this.origin = origin;
        this.map = map;
        this.directionsService = new google.maps.DirectionsService;
        this.directionsDisplay = new google.maps.DirectionsRenderer;
        this.directionsDisplay.setMap(map);
        this.placesService = new google.maps.places.PlacesService(map);
        this.infowindow = new google.maps.InfoWindow;
        this.infowindowContent = document.getElementById('infowindow-content');
        this.infowindow.setContent(this.infowindowContent);

        // Listen for clicks on the map.
        this.map.addListener('click', this.handleClick.bind(this));
    };


    ClickEventHandler.prototype.handleClick = function(event) {

        var latlngStr = event.latLng.toString();
        var latlng = latlngStr.substring(1, latlngStr.length - 1);
        latlng = latlng.split(',');
        var lat = latlng[0];
        var lng = latlng[1].substring(1, latlng[1].length);

        $('.inputs_lat').val(lat);
        $('.inputs_lng').val(lng);

        // If the event has a placeId, use it.
        if (event.placeId) {

            // Calling e.stop() on the event prevents the default info window from
            // showing.
            // If you call stop here when there is no placeId you will prevent some
            // other map click event handlers from receiving the event.
            event.stop();
            this.calculateAndDisplayRoute(event.placeId);
            this.getPlaceInformation(event.placeId);
        }
    };

    ClickEventHandler.prototype.calculateAndDisplayRoute = function(placeId) {
        var me = this;
        this.directionsService.route({
            origin: this.origin,
            destination: {
                placeId: placeId
            },
            travelMode: 'WALKING'
        }, function(response, status) {
            if (status === 'OK') {
                me.directionsDisplay.setDirections(response);
            } else {
                window.alert('Directions request failed due to ' + status);
            }
        });
    };

    ClickEventHandler.prototype.getPlaceInformation = function(placeId) {
        var me = this;
        this.placesService.getDetails({
            placeId: placeId
        }, function(place, status) {
            if (status === 'OK') {
                me.infowindow.close();
                me.infowindow.setPosition(place.geometry.location);
                me.infowindowContent.children['place-icon'].src = place.icon;
                me.infowindowContent.children['place-name'].textContent = place.name;
                me.infowindowContent.children['place-id'].textContent = place.place_id;
                me.infowindowContent.children['place-address'].textContent =
                    place.formatted_address;
                me.infowindow.open(me.map);
            }
        });
    };

</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyByjX-fIiEVgNTofLuWWpxgGqQADaoNSWk&libraries=places&callback=initAutocomplete" async defer></script>
