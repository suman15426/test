document.getElementById("shortenForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const response = await fetch("/shorten", {
    method: "POST",
    body: new URLSearchParams(formData),
  });
  const text = await response.text();
  document.getElementById("result").innerText = text;
});