const communityPostId = window.location.pathname.split('/').pop(); // 게시글 ID 가져오기
console.log("게시글 아이디: ", communityPostId);

function isValidPostId(id) {
  return !isNaN(id) && id.trim() !== "";
}

document.addEventListener("DOMContentLoaded", function () {
  if (isValidPostId(communityPostId)) {
    document.getElementById("page-type").textContent = "동네생활 글 수정하기";
    document.getElementById("submit-btn").textContent = "수정완료";
    fetch(`/api/community/posts/${communityPostId}`)
    .then(response => {
      if (!response.ok) {
        throw new Error("해당 게시글을 불러올 수 없습니다.");
      } else {
        return response.json();
      }
    })
    .then(data => {
      console.log(data);
      document.getElementById("community-post-title").value = data.title;
      editor.setData(data.content);
      document.getElementById(
          "community-post-category").value = data.communityCategoryId;
    })
    .catch(error => console.error("에러 발생: ", error));
    document.getElementById("submit-btn").addEventListener('click',
        function (e) {
          e.preventDefault();

        });

  } else {
    document.getElementById("page-type").textContent = "동네생활 글쓰기";
    document.getElementById("submit-btn").textContent = "작성완료";
    document.getElementById("community-post-title").value = "";
    editor.setData("");

    document.getElementById("submit-btn").addEventListener('click',
        function (e) {
          e.preventDefault();
          const editorContent = editor.getData();
          const form = document.getElementById('community-post-form');
          const formData = {
            userId: form.userId.value,
            communityCategoryId: form.communityCategoryId.value,
            title: form.title.value,
            content: editorContent
          };
          fetch('/api/community/posts', {
            method: 'post',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
          })
          .then(response => response.json())
          .then(postId => {
            window.location.href = `/community/posts/${postId}`;

          })
          .catch(error => console.error('Error 발생: ', error));
        });
  }
});

document.addEventListener("DOMContentLoaded", function () {
  fetch("/api/community/categories")
  .then(response => response.json())
  .then(data => {
    const categorySelect = document.getElementById("community-post-category");
    const parentCategories = data.filter(category => !category.parentId);

    parentCategories.forEach(parentCategory => {
      const optgroup = document.createElement("optgroup");
      optgroup.label = parentCategory.name;

      const childCategories = data.filter(
          category => category.parentId === parentCategory.id);
      childCategories.forEach(childCategory => {
        const option = document.createElement("option");
        option.value = childCategory.id;
        option.textContent = childCategory.name;
        optgroup.appendChild(option);
      });

      categorySelect.appendChild(optgroup);
    });
  })
  .catch(error => console.error("카테고리 데이터를 불러오지 못했습니다 : ", error));
})
