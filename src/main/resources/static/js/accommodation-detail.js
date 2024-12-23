document.addEventListener("DOMContentLoaded", function () {
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
        function renderRoomDetail(data) {
            const roomDetailContainer = document.getElementById("room-detail-info");

            roomDetailContainer.innerHTML = '';

            data.forEach(data => {

            })

            })
        }

        function displayErrorMessage(){
            alert("해당 숙소를 이용할 수 없습니다.");
        }
    }
})