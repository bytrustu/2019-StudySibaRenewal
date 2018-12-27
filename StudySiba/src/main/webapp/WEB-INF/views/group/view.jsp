<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/js/groupmessage.js"></script>

<div class="content_subject">
    <div class="content_subjectleft">
        <img src="/images/sub/search.png">
        <span>스터디그룹</span>
    </div>
</div>

<div class="group_warp">
    <input type="hidden" id="group_gNo" value="${view.gNo }">
    <div class="form-row mb-sm-4 group_title">
        <img src="/images/sub/siba-default.png">
        <span>${view.gName }</span>
        <c:if test="${view.id eq sessionScope.userSession.id }">
        	<button class="btn btn-danger group_deleteBtn">그룹삭제</button>
        </c:if>
        <c:if test="${view.id ne sessionScope.userSession.id }">
        	<button class="btn btn-danger group_outBtn">그룹탈퇴</button>
        </c:if>
    </div>

    <div class="form-row">
        <div class="form-group col-sm-6">
            <div class="form-row group_ltwarp">
                <div class="form-group col-sm-6 ltwarp_map">
                    <div class="ltwrp_mapview" id="room_map">

                    </div>
                </div>
                <div class="form-group col-sm-6">
                    <div class="ltwarp_address">
                        <img src="/images/sub/global.png"> <span>주소</span>
                        <p>${view.address }</p>
                        <img src="/images/sub/calendar.png"> <span>기간</span>
                        <p>${view.toPer }~${view.fromPer }</p>
                        <img src="/images/sub/circular-clock.png"> <span>시간</span>
                        <p>${view.toTime }~${view.fromTime }</p>
                    </div>

                </div>

            </div>
            <div class="form-row mt-sm-4 group_lbwarp">
                <div class="lbwarp_top">
                    <div class="lbwarp_title">
                        <span>첨부자료</span>
                    </div>
                    <button class="btn btn-danger modal_open" data="uploadModal">등록</button>
                </div>

                <div class="lbwarp_bottom">

                    <c:forEach items="${list }" var="list">
                        <div class="lbwarp_list form-row">
                            <div class="form-group col-sm-3">
                                ${list.uDate }
                            </div>
                            <div class="form-group col-sm-5">
                                ${list.content }
                            </div>
                            <div class="form-group col-sm-4">
                                <a href="">${list.uFile }</a>
                            </div>
                        </div>
                    </c:forEach>


                    <div class="content_pagenation group_page">
                        <ul class="pagination">
                            <c:if test="${page.startPage > page.pageBlock }">
                                <li class="page-item"><a class="page-link" href="/group/view?gNo=${view.gNo }&pageNum=${page.startPage-1 }">이전</a></li>
                            </c:if>
                            <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }" step="1">
                                <c:if test="${page.pageNum eq i }">
                                    <li class="page-item active"><a class="page-link" href="/group/view?gNo=${view.gNo }&pageNum=${i }">${i }</a></li>
                                </c:if>
                                <c:if test="${page.pageNum ne i }">
                                    <li class="page-item"><a class="page-link" href="/group/view?gNo=${view.gNo }&pageNum=${i }">${i }</a></li>
                                </c:if>
                            </c:forEach>
                            <c:if test="${page.endPage < page.pageCount }">
                                <li class="page-item"><a class="page-link" href="/group/view?gNo=${view.gNo }&pageNum=${page.startPage+page.pageBlock }">다음</a></li>
                            </c:if>
                        </ul>
                    </div>

                </div>

            </div>
        </div>
        <div class="form-group col-sm-6 group_rtwarp">
            <div class="form-row group_messenger">
                <div class="form-row messenger_top">
                    <img src="/images/sub/messenger.png">
                    <span>그룹대화함</span>
                </div>
                <div class="messenger_body">
                    <c:forEach items="${message }" var="message">
                        <div class="messenger_message">
                            <img class="rounded-circle" src="/images/profile/kakao/${message.proFile }">
                            <div class="messeger_messagewarp">
                                <div class="messenger_messagetop">
                                    <div>
                                        ${message.nick }
                                    </div>
                                    <div>
                                        ${message.gDate }
                                    </div>
                                </div>
                                <div class="clear-fix"></div>
                                <div class="messenger_messagebottom">
                                    ${message.content }
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <div class="form-row messenger_footer">
                    <input type="text" class="groupMessageText" maxlength="25">
                    <button class="btn btn-primary groupMessageBtn" data="open">전송</button>
                </div>
            </div>
        </div>
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
