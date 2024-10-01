const express = require('express');
const path = require('path');
const cors = require('cors');

const app = express();
const PORT = 8080; // 여기서 8080으로 포트를 변경

// CORS 설정
app.use(cors());

// 네이버 지도 API 사용을 위한 정적 파일 서빙
app.use(express.static(path.join(__dirname, 'public')));

// 서버 루트 경로에 index.html을 전달
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public/index.html'));
});

app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});
