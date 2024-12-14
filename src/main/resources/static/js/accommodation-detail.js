// document.addEventListener("DOMContentLoaded", function () {
//     const roomId = document.querySelector('input[name="roomId"]').value;
//     console.log(roomId);
//
//     if(roomId) {
//         fetch(`api/guest/room/detail/${roomId}`)
//             .then(response => response.json())
//             .then(data => {
//                 renderRoomDetail(data);
//             })
//             .catch(error => {
//                 console.error("error: ", error);
//                 displayErrorMessage();
//             })
//         function renderRoomDetail(data) {
//             const room = data.room;
//             const amenities = data.amenityIds;
//             const profile = data.profile;
//             const reviews = data.reviews;
//
//             data.forEach(info => {
//
//             })
//         }
//     }
// })