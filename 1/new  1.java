protected void updateStatusModifiedDate(Long userId, boolean status, boolean wasActive)
			throws PortalException, SystemException {
		// activate or deactivate date.

		if (userId == null)
			return;

		boolean pending = getUserPendingValue(userId);
		ExpandoValue activateDate = ExpandoUtil.getExpandoValue(
				ExpandoAttrType.ACTIVATE_DATE.getAttribute(), userId);
		
		ExpandoValue deactivateDate = ExpandoUtil.getExpandoValue(
				ExpandoAttrType.DEACTIVATE_DATE.getAttribute(), userId);
		System.out.println(wasActive);
		System.out.println(pending);
		System.out.println(status);
		if ((!wasActive || pending) && status) {

			if (activateDate != null) {
				activateDate.setDate(new Date());
				ExpandoValueLocalServiceUtil.updateExpandoValue(activateDate);
			} else {
				ExpandoUtil.addExpandoValue(
						ExpandoAttrType.ACTIVATE_DATE.getAttribute(), userId,
						String.valueOf(new Date().getTime()));
			} 
		} else if (((pending || wasActive) && (deactivateDate == null || wasActive)) && !status) {

			if (deactivateDate != null) {
				deactivateDate.setDate(new Date());
				ExpandoValueLocalServiceUtil.updateExpandoValue(deactivateDate);
			} else {
				ExpandoUtil.addExpandoValue(
						ExpandoAttrType.DEACTIVATE_DATE.getAttribute(), userId,
						String.valueOf(new Date().getTime()));
			}
		}
user2
	}