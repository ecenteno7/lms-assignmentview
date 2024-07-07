export const Post = ({ postDetails }) => {
  return (
    <div className="m-4 rounded-xl bg-slate-200 w-full min-h-60 overflow-auto">
      <p className="font-bold text-2xl text-left p-4">{postDetails.title}</p>
      <p className="font-semibold text-xl text-left pl-4">{`${postDetails.firstName} ${postDetails.lastName}`}</p>
      <p className="text-left p-4">{postDetails.content}</p>
      {postDetails.tags.map(tag => {
        return (
          <div className="w-fit h-10 ml-4 flex justify-center items-center rounded-xl bg-slate-800 p-3 mb-4 text-white font-semibold">
            <p className="text-left">{tag.name}</p>
          </div>
        )
      })}
    </div>
  )
}
