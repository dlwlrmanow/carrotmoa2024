document.addEventListener("DOMContentLoaded", function () {
    const urlParam = new URLSearchParams(window.location.search);
    const keyword = urlParam.get("keyword");
    document.title = `${keyword} 단기 임대 숙소 | 당근모아`;

    // 검색 결과 가져오기
    if(keyword) {
        fetch(`/api/guest/search?keyword=${keyword}`)
            .then(response => response.json()) // json객체로 데이터 받아오기
            .then(rooms => {
                if(!rooms || rooms.length === 0) {
                    // document.getElementById("results").innerHTML = '<p> 검색 결과 없음 </p>';
                    noResultAlertMessage();
                } else {
                    renderKeywordSearchResults(rooms);
                }
            })
            .catch(error => {
                console.error("error: ", error);
                displayErrorMessage();
            })
    }
    function renderKeywordSearchResults(rooms) {
        const roomListContainer = document.getElementById("room-list");
        const roomTemplate = document.getElementById('item-template');

        roomListContainer.innerHTML = '';

        rooms.forEach(room => {
            const roomClone = roomTemplate.content.cloneNode(true);

            const hiddenInput = roomClone.querySelector('input[name="id"]');
            hiddenInput.value = room.id;
            console.log(room.id);
            console.log(room.imageUrl);

            const detailLink = roomClone.querySelector('#item-detail');
            detailLink.href = `/room/detail/${room.id}`;

            roomClone.querySelector('img').src = room.imageUrl;
            roomClone.querySelector('#item-title').textContent = room.title;
            roomClone.querySelector('#item-addr').textContent = room.roadAddress;
            roomClone.querySelector('#item-price').textContent = `${room.price.toLocaleString()} 원/1주`;
            roomClone.querySelector('#item-space').textContent = `방 ${room.roomTypeCount[0]} 개/ 화장실 ${room.roomTypeCount[1]} 개/ 거실 ${room.roomTypeCount[2]} 개/ 주방 ${room.roomTypeCount[3]} 개`;

            roomListContainer.appendChild(roomClone);
        })
    }
    function noResultAlertMessage(){
        alert("검색 결과가 존재하지 않습니다.");
    }
    function displayErrorMessage(error){
        alert("에러 발생: " + error.message());
    }
})