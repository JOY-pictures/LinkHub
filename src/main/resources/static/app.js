
const userIdInput = document.getElementById("userIdInput");
const limitInput = document.getElementById("limitInput");
const refreshFeedButton = document.getElementById("refreshFeedButton");
const feedContainer = document.getElementById("feedContainer");

refreshFeedButton.addEventListener("click", refreshFeed)

async function refreshFeed() {
    try {
        const userId = userIdInput.value;
        const limit = limitInput.value;

        const response = await fetch(`/feeds?userId=${userId}&limit=${limit}`);
        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.message || data.error || "Ошибка при загрузке ленты");
        }

        if (!Array.isArray(data)) {
            throw new Error("Backend вернул не список");
        }

        renderFeed(data);
    } catch (error) {
        console.error(error);

        feedContainer.innerHTML = `
            <p><strong>Ошибка: </strong> ${error.message}</p>
        `;
    }
}

function renderFeed(posts) {
    try {
        feedContainer.innerHTML = "";
        for (const post of posts) {
            const article = document.createElement("article");

            article.innerHTML = `
                <h3>Пост #${post.postId}</h3>
                <p><strong>Автор:</strong> ${post.authorId}</p>
                <p>${post.text}</p>
                <p><strong>Режим:</strong> ${post.modeName}</p>
                <p><strong>Почему показан:</strong> ${post.reason}</p>

                ${renderReactions(post.postId, post.reactions)}
            `;

            feedContainer.appendChild(article);
        }
    } catch (error) {
        console.error(error);
    }
}

function renderReactions(postId, reactions) {
    if (!reactions) {
        return `<p><strong>Реакции:</strong> нет данных</p>`
    }

    return `
        <div>
            <strong>Реакции:</strong>
            <button onclick="setReaction(${postId}, 'USEFUL')">
                Useful ${reactions.usefulCount}
            </button>
            <button onclick="setReaction(${postId}, 'FUNNY')">
                Funny ${reactions.funnyCount}
            </button>
            <button onclick="setReaction(${postId}, 'INSPIRING')">
                Inspiring ${reactions.inspiringCount}
            </button>
            <button onclick="setReaction(${postId}, 'CALM')">
                Calm ${reactions.calmCount}
            </button>
        </div>`
}

async function setReaction(postId, reaction) {
    try {
        const userId = userIdInput.value;

        const response = await fetch(`/reactions`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                userId: userId,
                postId: postId,
                reactionType: reaction
            })
        })

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.message || data.error || "Ошибка при постановке реакции");
        }

        console.log("Реакция поставлена")
    } catch (error) {
        console.error(error);
        alert("Не удалось поставить реакцию: " + error.message);
    }
}

const changeModeButton = document.getElementById("changeModeButton");
const modeSelect = document.getElementById("modeSelect");

changeModeButton.addEventListener("click", changeMode)
async function changeMode() {
    try {
        const userId = userIdInput.value;
        const modeName = modeSelect.value;

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

        if (!response.ok) {
            throw new Error(result.message || result.error || "Ошибка при смене режима");
        }
        console.log("Сменён режим: " + result);

        await refreshFeed();
    } catch (error) {
        console.error(error);
        alert("Не удалось сменить режим: " + error.message);
    }
}

const authorIdInput = document.getElementById("authorIdInput");
const postModeSelect = document.getElementById("postModeSelect");
const postTextInput = document.getElementById("postTextInput");
const createPostButton = document.getElementById("createPostButton");

createPostButton.addEventListener("click", createPost)

async function createPost() {
    try {
        const authorId = authorIdInput.value
        const modeName = postModeSelect.value
        const text = postTextInput.value

        if (text.length < 1) {
            alert("Нельзя создать пустой пост")
        }

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

        if (!response.ok) {
            throw new Error(result.message || result.error || "Ошибка при создании поста");
        }
        postTextInput.value = ""

        console.log(result)
    } catch (error) {
        console.error(error);
        alert("Не удалост создать пост")
    }
}


