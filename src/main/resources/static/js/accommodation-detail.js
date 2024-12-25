document.addEventListener("DOMContentLoaded", function () {
    const roomDetailContainer = document.getElementById("room-detail-info");

    roomDetailContainer.addEventListener("click", function (event) {
        const card = event
    })

    const roomId = document.querySelector('input[name="id"]').value;
    const url = `/api/guest/room/detail?id=${roomId}`;
    console.log(roomId);

    if(roomId) {
        fetch(url)
            .then(response => response.json())
            .then(data => {
                renderRoomDetail(data);
            })
            .catch(error => {
                displayErrorMessage();
            })
    }

    function renderRoomDetail(data) {
        // const roomDetailContainer = document.getElementById("room-detail-info");
        roomDetailContainer.innerHTML = '';

        data.forEach(data => {
            const roomInfo = roomDetailContainer.content.cloneNode(true);

            const
        })

    }

    function displayErrorMessage(){
        alert("해당 숙소를 이용할 수 없습니다.");
    }
})