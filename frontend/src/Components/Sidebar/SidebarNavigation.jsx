import HomeIcon from "@mui/icons-material/Home"
import ExploreIcon from "@mui/icons-material/Explore"
import NotificationIcon from "@mui/icons-material/Notifications"
import MessageIcon from "@mui/icons-material/Message"
import ListAltIcon from "@mui/icons-material/ListAlt"
import GroupIcon from "@mui/icons-material/Group"
import VerifiedIcon from "@mui/icons-material/Verified"
import AccountCircleIcon from "@mui/icons-material/AccountCircle"
import ControlPointIcon from "@mui/icons-material/ControlPoint"
// import PendingIcon from "@mui/icons-material/Pending"

export const navigationMenu = [
  {
    title: "Home",
    icon: <HomeIcon />,
    path: "/home"
  },
  {
    title: "Reels",
    icon: <ExploreIcon />,
    path: "/reels"
  },
  {
    title: "Create Reels",
    icon: <ControlPointIcon />,
    path: "/create-reels"
  },
  {
    title: "Notifications",
    icon: <NotificationIcon />,
    path: "/notifications"
  },
  {
    title: "Messages",
    icon: <MessageIcon />,
    path: "/message"
  },
  {
    title: "Lists",
    icon: <ListAltIcon />,
    path: "/list"
  },
  {
    title: "Communities",
    icon: <GroupIcon />,
    path: "/communities"
  },
  {
    title: "Profile",
    icon: <AccountCircleIcon />,
    path: "/profile"
  },
  {
    title: "Verified",
    icon: <VerifiedIcon />,
    path: "/verified"
  },
  // {
  //   title: "More",
  //   icon: <PendingIcon />,
  //   path: "/more"
  // }
]