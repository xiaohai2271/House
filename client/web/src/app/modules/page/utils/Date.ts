export const clearTime = (date) => {
  const dateNew = new Date(date)
  dateNew.setHours(0, 0, 0, 0)
  return dateNew;
}

export const isEqual = (date1: Date, date2: Date) => {
  return date1 == date2 || date1.getTime() == date2.getTime() || date1.toISOString() == date2.toISOString()
}
