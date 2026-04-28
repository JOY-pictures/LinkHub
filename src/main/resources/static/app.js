
const userIdInput = document.getElementById("userIdInput");
const limitInput = document.getElementById("limitInput");
const refreshFeedButton = document.getElementById("refreshFeedButton");
const feedContainer = document.getElementById("feedContainer");

refreshFeedButton.addEventListener("click", refreshFeed)

async function refreshFeed() {
    const userId = userIdInput.value
    const limit = limitInput.value

    const response = await fetch(`/feeds?userId=${userId}&limit=${limit}`)
    const posts = await response.json();

    renderFeed(posts);
}

function renderFeed(posts) {
    feedContainer.innerHTML = "";
    for (const post of posts) {
        const article = document.createElement("article");

        article.innerHTML = `
            <h3>Пост #${post.postId}</h3>
            <p><strong>Автор:</strong> ${post.authorId}</p>
            <p>${post.text}</p>
            <p><strong>Режим:</strong> ${post.modeName}</p>
            <p><strong>Почему показан:</strong> ${post.reason}</p>
        `;

        feedContainer.appendChild(article);
    }
}

const changeModeButton = document.getElementById("changeModeButton");
const modeSelect = document.getElementById("modeSelect");

changeModeButton.addEventListener("click", changeMode)
async function changeMode() {
    const userId = userIdInput.value
    const modeName = modeSelect.value

    const command = {
        modeName: modeName,
    }

    const response = await fetch(`/users/${userId}/mode`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(command)
    })

    const result = await response.json();
    console.log(result)
}

const authorIdInput = document.getElementById("authorIdInput");
const postModeSelect = document.getElementById("postModeSelect");
const postTextInput = document.getElementById("postTextInput");
const createPostButton = document.getElementById("createPostButton");

createPostButton.addEventListener("click", createPost)

async function createPost() {
    const authorId = authorIdInput.value
    const modeName = postModeSelect.value
    const text = postTextInput.value
    postTextInput.value = ""
    const request = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            authorId: authorId,
            text: text,
            modeName: modeName,
        })
    }

    const response = await fetch(`/posts`, request)

    const result = await response.json();
    console.log(result)
}


