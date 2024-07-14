import axios from "./axios"

export const userLogin = async (username, password) => {
  const res = await axios.post('/api/login', {
    username: username,
    password: password
  })
  return res
}

export const getDiscussionPostList = async (courseId) => {
  const res = await axios.get(`/api/courses/${courseId}/discussion-posts`)
  return res
}


export const getDiscussionPostDetails = async (courseId, postId) => {
  const res = await axios.get(`/api/courses/${courseId}/discussion-posts/${postId}`)
  return res
}

export const createDiscussionPost = async (courseId, post) => {
  const res = await axios.post(`/api/courses/${courseId}/discussion-posts`, post)
  return res
}

export const getTags = async (courseId) => {
  const res = await axios.get(`/api/courses/${courseId}/tags`)
  return res
}

export const fetchAuthorDetails = async (authorId, courseId) => {
  const res = await axios.get(`/api/admin/courses/${courseId}/users/${authorId}`)
  return res
}

export const createDiscussionPostReply = async (authorId, courseId, discussionPostId, reply) => {
  const res = await axios.post(`/api/courses/${courseId}/discussion-posts/${discussionPostId}/responses`, {
    responses: [
      {
        authorID: authorId,
        classID: courseId,
        content: reply
      }
    ]
  })
  return res
}

export const markAcceptedAnswer = async (discussionResponseId, acceptedAnswer, courseId, discussionPostId) => {
  const res = await axios.patch(`/api/courses/${courseId}/discussion-posts/${discussionPostId}/responses/${discussionResponseId}`, {
    responses: [
      {
        discussionResponseID: discussionResponseId,
        accepted: acceptedAnswer
      }
    ]
  })
  return res
}

export const getAssignmentList = async (courseId) => {
  const res = await axios.get(`/api/courses/${courseId}/assignments`)
  return res
}

export const getAssignmentDetails = async (courseId, assignmentId) => {
  const res = await axios.get(`/api/courses/${courseId}/assignments/${assignmentId}`)
  return res
}

