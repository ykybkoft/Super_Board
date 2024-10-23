function searchByEmail() {
    const email = document.getElementById("searchEmail").value;
    if (email) {
        const url = `/api/posts/search?author_email=${encodeURIComponent(email)}`;

        // Fetch API를 사용하여 GET 요청 보내기
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // 데이터를 처리하여 테이블을 업데이트하는 부분
                const tableBody = document.querySelector(".body");
                tableBody.innerHTML = ""; // 기존 내용을 지우고
                data.forEach(post => {
                    const row = document.createElement("tr");
                    row.className = "body";
                    row.innerHTML = `
                <td>${post.id}</td>
                <td class="title">${post.title}</td>
                <td class="author">${post.author}</td>
                <td class="author">${post.createdAt}</td>
              `;
                    tableBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    } else {
        alert("이메일을 입력해 주세요.");
    }
}