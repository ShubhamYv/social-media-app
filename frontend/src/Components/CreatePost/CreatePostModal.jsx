import { Avatar, Backdrop, Box, Button, CircularProgress, IconButton, Modal } from '@mui/material';
import { useFormik } from 'formik';
import React, { useState } from 'react'
import ImageIcon from '@mui/icons-material/Image';
import VideocamIcon from '@mui/icons-material/Videocam';
import { uploadToCloudinary } from '../../Utils/uploadToCloudinary';
import { useDispatch } from 'react-redux';
import { createPostAction } from '../../Redux/Post/post.action';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 500,
  bgcolor: 'background.paper',
  boxShadow: 24,
  p: 4,
  borderRadius: ".6rem",
  outline: "none"
};


const CreatePostModal = ({ open, handleClose }) => {
  const [selectedImage, setSelectedImage] = useState()
  const [selectedVideo, setSelectedVideo] = useState()
  const [isLoading, setIsLoading] = useState(false)
  const dispatch = useDispatch()

  const formik = useFormik({
    initialValues: {
      caption: "",
      image: "",
      video: ""
    },
    onSubmit: (values) => {
      console.log("formik values", values)
      dispatch(createPostAction(values))
    }
  });

  const handleSelectImage = async (event) => {
    setIsLoading(true)
    const imageUrl = await uploadToCloudinary(event.target.files[0], "image")
    setSelectedImage(imageUrl)
    setIsLoading(false)
    formik.setFieldValue("image", imageUrl)
  }

  const handleSelectVideo = async (event) => {
    setIsLoading(true)
    const videoUrl = await uploadToCloudinary(event.target.files[0], "video")
    setSelectedVideo(videoUrl)
    setIsLoading(false)
    formik.setFieldValue("video", videoUrl)
  }

  return (
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <form onSubmit={formik.handleSubmit} action="">
          <div >
            <div className='flex space-x-4 items-center '>
              <Avatar />
              <div>
                <p className='font-bold text-lg'>Shubham Yadav</p>
                <p className='text-sm'>@shubham.yv</p>
              </div>
            </div>
            <textarea
              className='outline-none w-full mt-5 p-2 bg-transparent border border-[#3b4054] rounded-sm'
              name="caption"
              placeholder='write caption...'
              id=""
              value={formik.values.caption}
              onChange={formik.handleChange}
              rows="4"
            />

            <div className='flex space-x-5 items-center mt-5'>
              <div>
                <input
                  type="file"
                  accept='image/*'
                  onChange={handleSelectImage}
                  style={{ display: "none" }}
                  id='image-input'
                />
                <label
                  htmlFor='image-input'>
                  <IconButton color='primary' component='span'>
                    <ImageIcon />
                  </IconButton>
                </label>
                <span>Image</span>
              </div>

              <div>
                <input
                  type="file"
                  accept='video/*'
                  onChange={handleSelectVideo}
                  style={{ display: "none" }}
                  id='video-input'
                />
                <label
                  htmlFor='video-input'>
                  <IconButton color='primary' component='span'>
                    <VideocamIcon />
                  </IconButton>
                </label>
                <span>Video</span>
              </div>
            </div>

            {selectedImage && <div>
              <img src={selectedImage} className='h-[10rem]' alt="" />
            </div>}

            {selectedVideo && <div>
              <img src={selectedVideo} className='h-[10rem]' alt="" />
            </div>}

            <div className='flex w-full justify-end'>
              <Button
                variant='contained'
                type='submit'
                sx={{ borderRadius: "1.5rem" }}
              >Post</Button>
            </div>
          </div>
        </form>

        <Backdrop
          sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
          open={isLoading}
          onClick={handleClose}
        >
          <CircularProgress color="inherit" />
        </Backdrop>
      </Box>
    </Modal>

  )
}

export default CreatePostModal
